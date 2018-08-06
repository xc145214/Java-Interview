# synchronized 关键字原理

**synchronized可以保证方法或者代码块在运行时，同一时刻只有一个方法可以进入到临界区，同时它还可以保证共享变量的内存可见性。**

众所周知 `synchronized` 关键字是解决并发问题常用解决方案，有以下三种使用方式:

- 同步普通方法，锁的是当前对象。
- 同步静态方法，锁的是当前 `Class` 对象。
- 同步块，锁的是 `()` 中的对象。


实现原理：
`JVM` 是通过进入、退出对象监视器( `Monitor` )来实现对方法、同步块的同步的。

具体实现是在编译之后在同步方法调用前加入一个 `monitor.enter` 指令，在退出方法和异常处插入 `monitor.exit` 的指令。

其本质就是对一个对象监视器( `Monitor` )进行获取，而这个获取过程具有排他性从而达到了同一时刻只能一个线程访问的目的。

而对于没有获取到锁的线程将会阻塞到方法入口处，直到获取锁的线程 `monitor.exit` 之后才能尝试继续获取锁。

流程图如下:

![](E:\Xiac\LearnSpace\Java-Interview\img\sync.jpg)


通过一段代码来演示:

```java
  public class Synchronize {
  
      /**
       * 同步静态方法。
       */
      public static synchronized void syncStaticMethod() {
          System.out.println("Synchronize static method");
      }
  
  
      /**
       * 同步普通方法
       */
      public synchronized void syncMethod() {
          System.out.println("Synchronize method");
      }
  
      /**
       * 同步对象。
       */
      public void syscObject() {
          synchronized (this) {
              System.out.println("Synchronize object");
          }
      }
  
      /**
       * 同步类。
       */
      public void syncClass() {
          synchronized (Synchronize.class) {
              System.out.println("Synchronize class");
          }
      }
  }
```

使用 `javap -c Synchronize` 可以查看编译之后的具体信息。

```
λ javap -c Synchronize.class
Compiled from "Synchronize.java"
public class com.crossoverjie.synchronize.Synchronize {
  public com.crossoverjie.synchronize.Synchronize();
    Code:
       0: aload_0
       1: invokespecial #1                  // Method java/lang/Object."<init>":()V
       4: return

  public static synchronized void syncStaticMethod();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #3                  // String Synchronize static method
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return

  public synchronized void syncMethod();
    Code:
       0: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       3: ldc           #5                  // String Synchronize method
       5: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
       8: return

  public void syscObject();
    Code:
       0: aload_0
       1: dup
       2: astore_1
       3: monitorenter
       4: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       7: ldc           #6                  // String Synchronize object
       9: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      12: aload_1
      13: monitorexit
      14: goto          22
      17: astore_2
      18: aload_1
      19: monitorexit
      20: aload_2
      21: athrow
      22: return
    Exception table:
       from    to  target type
           4    14    17   any
          17    20    17   any

  public void syncClass();
    Code:
       0: ldc           #7                  // class com/crossoverjie/synchronize/Synchronize
       2: dup
       3: astore_1
       4: monitorenter
       5: getstatic     #2                  // Field java/lang/System.out:Ljava/io/PrintStream;
       8: ldc           #8                  // String Synchronize class
      10: invokevirtual #4                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      13: aload_1
      14: monitorexit
      15: goto          23
      18: astore_2
      19: aload_1
      20: monitorexit
      21: aload_2
      22: athrow
      23: return
    Exception table:
       from    to  target type
           5    15    18   any
          18    21    18   any
}
```

可以看到在同步代码块是使用 `monitorenter`和`monitorexit`指令实现的。

synchronized方法则会被翻译成普通的方法调用和返回指令如:invokevirtual、areturn指令，在VM字节码层面并没有任何特别的指令来实现被synchronized修饰的方法，而是在Class文件的方法表中将该方法的access_flags字段中的synchronized标志位置1，表示该方法是同步方法并使用调用该方法的对象或该方法所属的Class在JVM的内部对象表示Klass做为锁对象。

### JVM中的锁优化

简单来说在JVM中 `monitorenter` 和 `monitorexit` 字节码依赖于底层的操作系统的Mutex Lock来实现的，但是由于使用Mutex Lock需要将当前线程挂起并从用户态切换到内核态来执行，这种切换的代价是非常昂贵的；然而在现实中的大部分情况下，同步方法是运行在单线程环境（无锁竞争环境）如果每次都调用Mutex Lock那么将严重的影响程序的性能。不过在jdk1.6中对锁的实现引入了大量的优化，如锁粗化（Lock Coarsening）、锁消除（Lock Elimination）、轻量级锁（Lightweight Locking）、偏向锁（Biased Locking）、适应性自旋（Adaptive Spinning）等技术来减少锁操作的开销。

+ 锁粗化（Lock Coarsening）：也就是减少不必要的紧连在一起的unlock，lock操作，将多个连续的锁扩展成一个范围更大的锁。

+ 锁消除（Lock Elimination）：通过运行时JIT编译器的逃逸分析来消除一些没有在当前同步块以外被其他线程共享的数据的锁保护，通过逃逸分析也可以在线程本地Stack上进行对象空间的分配（同时还可以减少Heap上的垃圾收集开销）。

+ 轻量级锁（Lightweight Locking）：这种锁实现的背后基于这样一种假设，即在真实的情况下我们程序中的大部分同步代码一般都处于无锁竞争状态（即单线程执行环境），在无锁竞争的情况下完全可以避免调用操作系统层面的重量级互斥锁，取而代之的是在monitorenter和monitorexit中只需要依靠一条CAS原子指令就可以完成锁的获取及释放。当存在锁竞争的情况下，执行CAS指令失败的线程将调用操作系统互斥锁进入到阻塞状态，当锁被释放的时候被唤醒（具体处理步骤下面详细讨论）。

+ 偏向锁（Biased Locking）：是为了在无锁竞争的情况下避免在锁获取过程中执行不必要的CAS原子指令，因为CAS原子指令虽然相对于重量级锁来说开销比较小但还是存在非常可观的本地延迟（可参考这篇文章）。

+ 适应性自旋（Adaptive Spinning）：当线程在获取轻量级锁的过程中执行CAS操作失败时，在进入与monitor相关联的操作系统重量级锁（mutex semaphore）前会进入忙等待（Spinning）然后再次尝试，当尝试一定的次数后如果仍然没有成功则调用与该monitor关联的semaphore（即互斥锁）进入到阻塞状态。

### java对象头

Hotspot虚拟机的对象头主要包括两部分数据：Mark Word（标记字段）、Klass Pointer（类型指针）。其中Klass Point是是对象指向它的类元数据的指针，虚拟机通过这个指针来确定这个对象是哪个类的实例，Mark Word用于存储对象自身的运行时数据，它是实现轻量级锁和偏向锁的关键，所以下面将重点阐述Mark Word。

Mark Word用于存储对象自身的运行时数据，如哈希码（HashCode）、GC分代年龄、锁状态标志、线程持有的锁、偏向线程 ID、偏向时间戳等等。Java对象头一般占有两个机器码（在32位虚拟机中，1个机器码等于4字节，也就是32bit），但是如果对象是数组类型，则需要三个机器码，因为JVM虚拟机可以通过Java对象的元数据信息确定Java对象的大小，但是无法从数组的元数据来确认数组的大小，所以用一块来记录数组长度。下图是Java对象头的存储结构（32位虚拟机）：

  

| 25bit          | 4bit           | 1bit         | 2bit     |
| -------------- | -------------- | ------------ | -------- |
| 对象的HashCode | 对象的分代年龄 | 是否是偏向锁 | 锁标志位 |

 对象头信息是与对象自身定义的数据无关的额外存储成本，但是考虑到虚拟机的空间效率，Mark Word被设计成一个非固定的数据结构以便在极小的空间内存存储尽量多的数据，它会根据对象的状态复用自己的存储空间，也就是说，Mark Word会随着程序的运行发生变化，不同的锁状态对应着不同的记录存储方式，可能值如下所示： （32位虚拟机）  

![](E:\Xiac\LearnSpace\Java-Interview\img\lock_status.png)

**无锁状态 ： 对象的HashCode + 对象分代年龄 + 状态位001** 

## Monitor

什么是Monitor？我们可以把它理解为一个同步工具，也可以描述为一种同步机制，它通常被描述为一个对象。  与一切皆对象一样，所有的Java对象是天生的Monitor，每一个Java对象都有成为Monitor的潜质，因为在Java的设计中 ，每一个Java对象自打娘胎里出来就带了一把看不见的锁，它叫做内部锁或者Monitor锁。  Monitor 是线程私有的数据结构，每一个线程都有一个可用monitor record列表，同时还有一个全局的可用列表。每一个被锁住的对象都会和一个monitor关联（对象头的MarkWord中的LockWord指向monitor的起始地址），同时monitor中有一个Owner字段存放拥有该锁的线程的唯一标识，表示该锁被这个线程占用。其结构如下：  



![img](E:\Xiac\LearnSpace\Java-Interview\img\monitor_record.png)

 

Owner：初始时为NULL表示当前没有任何线程拥有该monitor record，当线程成功拥有该锁后保存线程唯一标识，当锁被释放时又设置为NULL；

EntryQ:关联一个系统互斥锁（semaphore），阻塞所有试图锁住monitor record失败的线程。

RcThis:表示blocked或waiting在该monitor record上的所有线程的个数。

Nest:用来实现重入锁的计数。

HashCode:保存从对象头拷贝过来的HashCode值（可能还包含GC age）。

Candidate:用来避免不必要的阻塞或等待线程唤醒，因为每一次只有一个线程能够成功拥有锁，如果每次前一个释放锁的线程唤醒所有正在阻塞或等待的线程，会引起不必要的上下文切换（从阻塞到就绪然后因为竞争锁失败又被阻塞）从而导致性能严重下降。Candidate只有两种可能的值0表示没有需要唤醒的线程1表示要唤醒一个继任线程来竞争锁。

## **轻量级锁具体实现：**

​     一个线程能够通过两种方式锁住一个对象：1、通过膨胀一个处于无锁状态（状态位001）的对象获得该对象的锁；2、对象已经处于膨胀状态（状态位00）但LockWord指向的monitor record的Owner字段为NULL，则可以直接通过CAS原子指令尝试将Owner设置为自己的标识来获得锁。

获取锁（monitorenter）的大概过程如下：

1. 当对象处于无锁状态时（RecordWord值为HashCode，状态位为001），线程首先从自己的可用moniter record列表中取得一个空闲的moniter record，初始Nest和Owner值分别被预先设置为1和该线程自己的标识，一旦monitor record准备好然后我们通过CAS原子指令安装该monitor record的起始地址到对象头的LockWord字段来膨胀该对象，如果存在其他线程竞争锁的情况而调用CAS失败，则只需要简单的回到monitorenter重新开始获取锁的过程即可。
2. 对象已经被膨胀同时Owner中保存的线程标识为获取锁的线程自己，这就是重入（reentrant）锁的情况，只需要简单的将Nest加1即可。不需要任何原子操作，效率非常高。
3. 对象已膨胀但Owner的值为NULL，当一个锁上存在阻塞或等待的线程同时锁的前一个拥有者刚释放锁时会出现这种状态，此时多个线程通过CAS原子指令在多线程竞争状态下试图将Owner设置为自己的标识来获得锁，竞争失败的线程在则会进入到第四种情况（4）的执行路径。
4. 对象处于膨胀状态同时Owner不为NULL(被锁住)，在调用操作系统的重量级的互斥锁之前先自旋一定的次数，当达到一定的次数时如果仍然没有成功获得锁，则开始准备进入阻塞状态，首先将rfThis的值原子性的加1，由于在加1的过程中可能会被其他线程破坏Object和monitor record之间的关联，所以在原子性加1后需要再进行一次比较以确保LockWord的值没有被改变，当发现被改变后则要重新进行monitorenter过程。同时再一次观察Owner是否为NULL，如果是则调用CAS参与竞争锁，锁竞争失败则进入到阻塞状态。

释放锁（monitorexit）的大概过程如下：

1. 首先检查该对象是否处于膨胀状态并且该线程是这个锁的拥有者，如果发现不对则抛出异常；
2. 检查Nest字段是否大于1，如果大于1则简单的将Nest减1并继续拥有锁，如果等于1，则进入到第（3）步；
3. 检查rfThis是否大于0，设置Owner为NULL然后唤醒一个正在阻塞或等待的线程再一次试图获取锁，如果等于0则进入到第4步
4. 缩小（deflate）一个对象，通过将对象的LockWord置换回原来的HashCode值来解除和monitor record之间的关联来释放锁，同时将monitor record放回到线程是有的可用monitor record列表。



## 锁优化
`synchronized`  很多都称之为重量锁，`JDK1.6` 中对 `synchronized` 进行了各种优化，为了能减少获取和释放锁带来的消耗引入了`偏向锁`和`轻量锁`。


### 轻量锁
当代码进入同步块时，如果同步对象为无锁状态时，当前线程会在栈帧中创建一个锁记录(`Lock Record`)区域，同时将锁对象的对象头中 `Mark Word` 拷贝到锁记录中，再尝试使用 `CAS` 将 `Mark Word` 更新为指向锁记录的指针。

如果更新**成功**，当前线程就获得了锁。

如果更新**失败** `JVM` 会先检查锁对象的 `Mark Word` 是否指向当前线程的锁记录。

如果是则说明当前线程拥有锁对象的锁，可以直接进入同步块。

不是则说明有其他线程抢占了锁，如果存在多个线程同时竞争一把锁，**轻量锁就会膨胀为重量锁**。

#### 解锁
轻量锁的解锁过程也是利用 `CAS` 来实现的，会尝试锁记录替换回锁对象的 `Mark Word` 。如果替换成功则说明整个同步操作完成，失败则说明有其他线程尝试获取锁，这时就会唤醒被挂起的线程(此时已经膨胀为`重量锁`)

轻量锁能提升性能的原因是：

认为大多数锁在整个同步周期都不存在竞争，所以使用 `CAS` 比使用互斥开销更少。但如果锁竞争激烈，轻量锁就不但有互斥的开销，还有 `CAS` 的开销，甚至比重量锁更慢。

### 偏向锁

为了进一步的降低获取锁的代价，`JDK1.6` 之后还引入了偏向锁。

偏向锁的特征是:锁不存在多线程竞争，并且应由一个线程多次获得锁。

当线程访问同步块时，会使用 `CAS` 将线程 ID 更新到锁对象的 `Mark Word` 中，如果更新成功则获得偏向锁，并且之后每次进入这个对象锁相关的同步块时都不需要再次获取锁了。

#### 释放锁
当有另外一个线程获取这个锁时，持有偏向锁的线程就会释放锁，释放时会等待全局安全点(这一时刻没有字节码运行)，接着会暂停拥有偏向锁的线程，根据锁对象目前是否被锁来判定将对象头中的 `Mark Word` 设置为无锁或者是轻量锁状态。

偏向锁可以提高带有同步却没有竞争的程序性能，但如果程序中大多数锁都存在竞争时，那偏向锁就起不到太大作用。可以使用 `-XX:-userBiasedLocking=false` 来关闭偏向锁，并默认进入轻量锁。


### 其他优化

#### 适应性自旋
在使用 `CAS` 时，如果操作失败，`CAS` 会自旋再次尝试。由于自旋是需要消耗 `CPU` 资源的，所以如果长期自旋就白白浪费了 `CPU`。`JDK1.6`加入了适应性自旋:

> 如果某个锁自旋很少成功获得，那么下一次就会减少自旋。

