# CS61B- s21



# Lecture

## Lecture8

### Dynamic Type and Static Type

```变量
List61B<String> someList = new SLList<>();
...
System.out.println(longest(someList));
```

someList有一个Static Type,就是List61B

someList有一个Dynamic Type,就是SLList

如果Dynamic Type中重写（注意！不是重载！）了Static Type中的内容，则用重写的内容替代

（这里假设一个变量，静态类型是动态类型的上一级。变量在调用函数时，如果函数在动态类型中重写了，将调用动态类型中的method，如果没有重写，则调用上层静态类型中的method，而如果调用一个静态类型中没有的method，则编译器会报错）



## Lecture9

### Use Inheritance as high-order function

定义一个类型，这个类型的变量是一个函数：

```
public interface IntUnaryFunction {
    int apply(int x);
}
```

定义这个类型中包含的函数，并且实现这个函数：

```
public class TenX implements IntUnaryFunction {
    /** Returns ten times the argument. */
    public int apply(int x) {
        return 10 * x;
    }
}
```

实现高阶函数：

先定义一个变量tenX,这个变量的类型是函数

```
public static int do_twice(IntUnaryFunction f, int x) {
        return f.apply(f.apply(x));
    }

    public static void main(String[] args) {
        IntUnaryFunction tenX = new TenX();
        System.out.println(do_twice(tenX, 2));
    }
}
```



## Lecture10

### Interface

interface:

```
public interface Ourcomparable {
    public int compareTo(Object o);
}
```

Dog class:

```
public class Dog implements Ourcomparable{
  ...
  public int compareTo(Object o){
    Dog uddaDog = (Dog) o;
    return this.size - uddaDog.size;
  }
  ...
}
```

Maximizer class:

```
public class Maximizer{
  public static Ourcomparable max(Ourcomparable[] items){
    ...
    int cmp = items[i].compareTo(items[maxDec]);
    ...
  }
  ...
}
```

Dog Launcher:

```
public class DogLauncher{
  public static void main(String[] args){
    ...
    Dog dogs[] = new Dog[]{d1, d2, d3};
    System.out.printIn(Maxi)
  }
}
```

### Comparable和Comparator

如果一个类Dog需要一个compareTo function来比较this dog与另一个dog,则可以将此类Dog继承Comparable类，然后在Dog类中实现compareTo function

如果一个类Dog需要一个compare function来比较dog a和dog b，则需要写一个private class，这个class继承comparator，在这个class中实现compare function，然后再在Dog类中写一个public function来生成一个上述private class的容器，这个public function的返回值是上述private class



## Lecture11

### Iteration

Set.java:

```
public Iterator<E> Iterator(); {
  return new setIterator;
}
private setIterator implements Iterator<E> {
  public boolean hasNext();
  public E next()
}
```

Nice Iteration:

```
Set<Integer> javaset = new HashSet<Integer>();
...
for (int x : javaset) {
   System.out.println(x);
}
```

Ugly Iteration:

```
Set<Integer> javaset = new HashSet<Integer>();
...
Iterator<Integer> seer = javaset.Iterator(); //返回一个
while (seer.hasNext()) {
  return seer.next();
}
```



## Lecture20

### Heap

#### binary min-heap

1. 最小二叉堆所有节点都小于等于他的两个孩子
2. 如果有missing node，则missing node一定在最底层，并且所有底层节点都在最靠左(complete tree,用一个数组来存储)

用Heap来实现PQ（优先级队列）



## Lecture21

### Tree Traversal

1. Preorder

   打印文件列表

2. Inorder

3. Postorder

   计算一个节点是他的孩子节点之和

### Graphs

不能有两条平行边

不能连接自己

### 深度优先遍历(DFS)

找出所有路径：

![image-20240809155246696](https://gitee.com/OooAlex/study_note/raw/master/img/202408091552921.png)

DFS Preorder:

```
dfs(v): 
   mark(v);
   if (!ismarked(w)); //判断v的邻居w是否已经被标记
       edgeTo[w] = v; //Action before DFS calls to neighbors
       dfs(w);
```

DFS Postorder:

```
dfs(v): 
   mark(v);
   if (!ismarked(w)); //判断v的邻居w是否已经被标记
       dfs(w); 
   print(v); //Action after DFS calls to neighbors
```



## Lecture22

### Graph Traversals

BFS: (non-recursive)

![image-20240809165316990](https://gitee.com/OooAlex/study_note/raw/master/img/202408091653082.png)

```
Queue fringe;
add(s);
marked(s);
disTo[s] = 0;
while(!isEmpty())
    v = removeFirst(); //remove each v from fringe;
    addLast(n); //add each unmarked n (v's neighbor) to the fringe;
    mark(n); //将入队的n标记
    edgeTo[n] = v;
    disTo[n] = disTo[v] + 1;
```

### Graph API

其中一种Graph的API:

```
public class Graph {
  public Graph(int V);
  public void addEdge(int v, int w);
  Iterable<Interger> adj(int v); //v的邻居（可迭代）
  int V();
  int E();
}
```

这种Graph的API要实现获取节点v的邻居的个数比较麻烦

### Graph Representation (How to implement the Graph API)

* adjacency matrix
* list of edges(用HarshSet保存Edge)
* adjacency list

不同的Graph Implementation将会使得DFS和BFS的runtime不一样

### DFS实现

假设Graph用Adjacency list表示：

```
public class DepthFirstPaths {
  private boolean[] marked;
  private int[] edgeTo;
  private int s; //头部节点
  
  public DepthFirstPaths(Graph G, int s) {
    将marked[]全标记为false;
    ...
    dfs(G, S);
  }
  
  private void dfs(Graph G, int v) {
    marked[v] = true;
    for (int w : G.adj(v)) {
      if (!marked[w]) {
        edgeTo[w] = v;
        dfs(G, w);
      }
    }
  }
  ...
}
```



## Lecture23

### Dijkstra算法 – 最短路径

![image-20240814220022356](https://gitee.com/OooAlex/study_note/raw/master/img/202408142200517.png)

伪代码：

![image-20240814220050896](https://gitee.com/OooAlex/study_note/raw/master/img/202408142200016.png)

首先创建一个priority queue，每个元素的优先级相同，都是无穷大

然后从source开始对指向的元素进行relax edge, 更新优先级队列，而后按照优先级出列，再对出列的元素指向的元素进行relax edge, 更新优先级队列，然后再按优先级出列…

```
PQ.add(source, 0)
For other vertices v, PQ.add(v,infinity)  //创建优先级队列
while PQ is not empty:
    p = PQ.removeSmallest();  //优先级最高的出列
    Relax all edges from p:   
        if (disTo[p] + w < disTo[q])
            {
                disTo[q] = disTo[p] + w;
                edgeTo[q] = p;
                PQ.changePriority(q, disTo[q]);  //更新优先级
                //runtime = E*logV
            }
```

算法能work的核心：对已经出列的元素进行relax edge一定会fail！

因此只需要relax edge PQ里面的元素就行了，当PQ为空，所有元素relax edge完毕

### Heuristic Search启发式搜索

![image-20240814224510130](https://gitee.com/OooAlex/study_note/raw/master/img/202408142245336.png)

PQ通过disTo(v) + h(v, goal)来排序priority，其中h(v, goal)是一个估算



## Lecture24

### Spanning Trees

* 非循环
* 所有节点相互连接

### MST

最小生成树

#### Prim's Algorithm

与Dijkstra类似

不同的是其disTo计算的是节点与MST的最短距离，而不是与某一顶点的距离

#### Kruskal's Algorithm

![image-20240820233154606](https://gitee.com/OooAlex/study_note/raw/master/img/202408202331122.png)

实现方式：

用PQ来储存所有的edge

通过WQU(Weighed Quick Union)来keep track有无cycle

![image-20240820233639850](https://gitee.com/OooAlex/study_note/raw/master/img/202408202336993.png)

### 三种算法比较

![image-20240820234817892](https://gitee.com/OooAlex/study_note/raw/master/img/202408202348973.png)



## Lecture25 — Multi-dimensional Data and Range Searching (QuadTree and K-d Tree)

### Multi-dimensional Data

如何使用Tree存储多维的数据？

### 二维 --- QuadTree

二维数据：

每一个节点有四个孩子，分别表示该节点的NW,NE,SE,SW方向

![image-20240901125354186](https://gitee.com/OooAlex/study_note/raw/master/img/202409011253364.png)

### K-d Tree

可以拓展到存储k维数据

![image-20240901132342445](https://gitee.com/OooAlex/study_note/raw/master/img/202409011323619.png)

### K-d Nearest

首先优先遍历节点的good side，再回头访问bad side, 如果bad side可以确定大于当前的nearest,则可以直接pruning这块区域

![image-20240901134135030](https://gitee.com/OooAlex/study_note/raw/master/img/202409011341249.png)

#### 伪代码

![image-20240901134933987](https://gitee.com/OooAlex/study_note/raw/master/img/202409011349057.png)

### Uniform Partitioning

将空间分为一个个小的bucket

![](https://gitee.com/OooAlex/study_note/raw/master/img/202409011411437.png)



# Lab

## Lab0

### Github仓库配置

创建目录cs61b，进入目录：

```
cd cs61b
```

打开github，创建一个新的repository，填写仓库名称(CS61B)

在cs61b目录下终端输入：

```
git init
git clone (上面创建的仓库链接)
```

进入clone下来的仓库,将cs61b所需的hw project lab 拉取过来：

```
cd CS61B
git remote add skeleton (老师创建的远程仓库链接)
git pull skeleton master
```

### 环境配置

使用git命令将代码上传至github仓库

```
git add lab1/*
git commit -m "...(message)"
git push -u origin main
```

在远程仓库修改内容后应该先将远程仓库修改的内容更新到本地仓库

```
git pull --rebase --autostash
```

然后再重复add commit push命令将本地仓库内容上传

restart:

先push上传，再运行命令：

```
git checkout origin/main -- proj0
```

就可以restart到刚才push时的状态



## Lab6

### File class methods

Files

```
File f = new File("dummy.txt"); //相对路径
f.createFile();
f.exists();
Utils.writeContents(f,"Hello World");
```

Directories

```
File d = new File("dummy");
d.mkdir();
```

### Serializable

```
import java.io.Serializable;

public class Model implements Serializable{
...
}
```

using helper functions from Utils class

````
Model m;
File outFile = new File(saveFileName);

writeObject(outFile, m);
````

```
Model m;
File infile = new File(saveFileName);

m = readObject(inFile, Model.class);
```

### Useful Util Functions

```
static void writeContents(File file, Object... contents);

static String readContentsAsString(File file); //reads in a file as a string

static byte[] readContents(File file); //reads in a file as a byte array

static void writeObject(File file, Serializable obj); //writes a serializable object to a file

static <T extends Serializable> T readObject(File file, Class<T> expectedClass);
(e.g.) Dog d = readObject(inFile, Dog.class);
```



## Lab8 HashMap

构建hash table时，可以用不同的data structures创建buckets

![image-20240901150722434](https://gitee.com/OooAlex/study_note/raw/master/img/202409011507552.png)

### Starter File

```
Map61B.java
└── MyHashMap.java
    ├── MyHashMapALBuckets.java
    ├── MyHashMapHSBuckets.java
    ├── MyHashMapLLBuckets.java
    ├── MyHashMapPQBuckets.java
    └── MyHashMapTSBuckets.java
```

### Starter Code

```
// Each Collection object is a single bucket, containing nodes.
private Collection<Node>[] buckets;
```

* `ArrayList`, `LinkedList`, `TreeSet`, `HashSet`, `PriorityQueue`这些data structures都是继承的`Collection` Interface。因此利用多态性(polymorphism), static type 为`Collection<Node>` 的objects可以用以上各种data structures实例化，e.g. `LinkedList<Node>` or `ArrayList<Node>`

* expression `new Collection<Node>[size]` is illegal, should create `new Collection[size]` 

每一个*Buckets classes instantiates `buckets` with a different type of data structure

例如：

```
protected Collection<Node> createBucket() {
	return new LinkedList<>();
}
```

### Implementation Requirements

1. 实现以下构造函数：

```
public MyHashMap();
public MyHashMap(int initialSize);
public MyHashMap(int initialSize, double loadFactor);
```

2. 实现keySet和iterator

> create a `HashSet` instance variable that holds all the keys

3. additional requirements for `MyHashMap`：

* hash map initially have a number of buckets equal to `initialSize`

* increase the size of `MyHashMap` when the load factor exceeds the set `loadFactor`

  $ loadFactor = N/M $

  `N` is the number of elements in the map and `M` is the number of buckets

* hashCode() method 可能会返回负值，要将此种情况考虑

![image-20240901154820365](https://gitee.com/OooAlex/study_note/raw/master/img/202409011548609.png)

![image-20240901154832467](https://gitee.com/OooAlex/study_note/raw/master/img/202409011548537.png)

Hashing过程:

![image-20240901161248774](https://gitee.com/OooAlex/study_note/raw/master/img/202409011612844.png)

* If the same key is inserted more than once, the value should be updated each time. `null` keys will never be inserted.

* Collection class provides methods needed to implement HashMap: `add`, `remove`, and `iterator`. When you are searching for a `Node` in a `Collection`, simply iterate over the `Collection`, and find the `Node` whose `key` is `.equal()` to the key you are searching for.

### Recall Inheritance & subtype polymorphism

![image-20240901213605201](https://gitee.com/OooAlex/study_note/raw/master/img/202409012136302.png)

![image-20240901213628580](https://gitee.com/OooAlex/study_note/raw/master/img/202409012136666.png)

![image-20240901213857774](https://gitee.com/OooAlex/study_note/raw/master/img/202409012138864.png)

o3的static type是Object, Object对象没有bark method, 因此`o3.bark()`错误。

### My Implementation

首先编写`createNode()`,`createTable()`,`createBucket()`

再编写构造函数，利用`createTable()`创建buckets数组, `createBucket()`创建每一个bucket

其中`createBucket()`会在*Bucket classes中重写

```
public MyHashMap() {
        Collection<Node>[] buckets = createTable(size);
        for (Collection<Node> bucket : buckets) {
            bucket = createBucket();
        }
    }

    public MyHashMap(int initialSize) {
        size = initialSize;
        Collection<Node>[] buckets = createTable(size);
        for (Collection<Node> bucket : buckets) {
            bucket = createBucket();
        }
    }

    public MyHashMap(int initialSize, double maxLoad) {
        size = initialSize;
        loadFactor = maxLoad;
        Collection<Node>[] buckets = createTable(size);
        for (Collection<Node> bucket : buckets) {
            bucket = createBucket();
        }
    }
    
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }
     protected Collection<Node> createBucket() {
        return null;
    }
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }
```
