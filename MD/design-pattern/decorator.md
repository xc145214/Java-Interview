# 装饰器模式

通过2种方式给一个类或者对象添加行为：
+ 使用继承

继承是给一个类添加行为的比较有效的途径。通过使用继承，可以使得子类在拥有自身方法的同时，还可以拥有父类的方法。但是使用继承是静态的，在编译的时候就已经决定了子类的行为，我们不便于控制增加行为的方式和时机。

+ 使用关联

组合即将一个对象嵌入到另一个对象中，由另一个对象来决定是否引用该对象来扩展自己的行为。这是一种动态的方式，我们可以在应用程序中动态的控制。

与继承相比，关联关系的优势就在于不会破坏类的封装性，且具有较好的松耦合性，可以使系统更加容易维护。但是它的缺点就在于要创建比继承更多的对象。

## 定义

饰者模式，动态地将责任附加到对象上。若要扩展功能，装饰者提供了比继承更加有弹性的替代方案。 

## 模式结构

**Component** : 抽象构件。是定义一个对象接口，可以给这些对象动态地添加职责。

**ConcreteComponent**:具体构件。是定义了一个具体的对象，也可以给这个对象添加一些职责。

**Decorator** : 抽象装饰类。是装饰抽象类，继承了Component,从外类来扩展Component类的功能，但对于Component来说，是无需知道Decorator存在的。

**ConcreteDecorator**:具体装饰类，起到给Component添加职责的功能。

