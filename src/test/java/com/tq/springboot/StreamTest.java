package com.tq.springboot;

import com.tq.springboot.juc.Student;
import com.tq.springboot.juc.ThreadPoolTest;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

public class StreamTest {
    //1、flatMap – 拍平嵌套集合 比如集合嵌套集合（List<List<T>>）、对象包含集合（如多个user有多个order）
    @Test
    public void flatMap(){
        // 创建嵌套的字符串列表
        List<List<String>> nestedStrings = Arrays.asList(
                Arrays.asList("苹果", "香蕉", "橙子"),  // 第一组水果
                Arrays.asList("咖啡", "茶"),         // 第二组饮料
                Arrays.asList("披萨", "汉堡", "","薯条") // 第三组快餐
        );
        // 使用flatMap将所有元素合并到一个列表中
        List<String> allItems = nestedStrings.stream()
                .flatMap(List::stream)  // 将每个List<String>转换为Stream<String>
                .collect(Collectors.toList());

        System.out.println(allItems);
        // 输出: [苹果, 香蕉, 橙子, 咖啡, 茶, 披萨, 汉堡, 薯条]
    }

    //2、partitioningBy将流元素按照给定的条件分成两个分区（true/false） 只能分成两组，true 键对应的列表包含所有满足条件的元素， false键对应的列表包含所有不满足条件的元素
//和groupingBy的差异在于，如果必须确保始终返回两个分组的场景用partitioningBy，且partitioningBy对空集合仍需保留分组结构返回{false=[], true=[]}
//Collectors.partitioningBy()第二个参数可以和特殊下游收集器配合，Collectors.groupingBy()第二个参数也可以进行比如多级分组、每组数量平均值 groupingBy(分类函数, 下游收集器)
    @Test
    public void partitioningBy(){
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        // 使用partitioningBy按奇偶性分区
        Map<Boolean, List<Integer>> partitioned = numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
        System.out.println("偶数: " + partitioned.get(true));
        System.out.println("奇数: " + partitioned.get(false));

        Map<Boolean, List<Integer>> group = numbers.stream().collect(Collectors.groupingBy(n -> n>10));
        System.out.println(group.get(false));

    }

    @Test
    public void test2() {

        //3、分组后统计：groupingBy + counting / summingInt / mapping
   /* 场景1： 按部门统计员工数：
    Map<String, Long> countByDept = users.stream()
            .collect(Collectors.groupingBy(User::getDepartment, Collectors.counting()));

    场景2： 每个部门所有用户名字拼接：
    Map<String, String> nameJoinByDept = users.stream()
            .collect(Collectors.groupingBy(
                    User::getDepartment,
                    Collectors.mapping(User::getName, Collectors.joining(","))
            ));*/

        //4、peek – 中间调试查看，比如排查 Stream 中间处理结果（调试用）
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

        List<String> result = names.stream()
                .peek(name -> System.out.println("处理前: " + name))
                .map(String::toUpperCase)
                .peek(name -> System.out.println("处理后: " + name)) //Java 9+ 中对于并行流，peek() 的执行顺序可能不确定
                .collect(Collectors.toList());
        /* 输出:
        处理前: Alice
        处理后: ALICE
        处理前: Bob
        处理后: BOB
        处理前: Charlie
        处理后: CHARLIE
        */

        //5、collectingAndThen收集器 收集+转换 它允许在常规收集操作完成后对结果进行额外的转换 常用于生成不可变集合、支持在收集后立即处理结果
        List<String> list1 = Arrays.asList("Alice", "Bob", "Charlie");
        // 转换为不可变列表
        List<String> tableList = list1.stream()
                .collect(Collectors.collectingAndThen(
                        Collectors.toList(),
                        Collections::unmodifiableList //设置不可变
                ));
// tableList.add("David"); // 抛出UnsupportedOperationException

 /*   分组后获取每班分数最高的学生
  List<Student> students = Arrays.asList(
            new Student("张三", "Class A", 85),
            new Student("李四", "Class B", 72),
            new Student("王五", "Class A", 90),
            new Student("赵六", "Class B", 68),
            new Student("钱七", "Class A", 88)
        );
    Map<String, Student> topStudentsByClass = students.stream()
            .collect(collectingAndThen(
                    groupingBy(                        // 第一步：分组收集这会产生一个 Map<String, Optional<Student>>
                            Student::getClassName,
                            maxBy(comparing(Student::getScore)) // 每组找分数最高的学生，maxBy 返回的是 Optional（可能组为空）
                    ),
                    map -> map.entrySet().stream()      // 第二步：转换处理  map是前面 groupingBy 收集器的中间结果Map<String, Optional<Student>>
                            .filter(e -> e.getValue().isPresent())// 过滤掉空Optional
                            .collect(toMap(
                                    Map.Entry::getKey,  // 保留原来的班级作为key
                                    e -> e.getValue().get() // 解包Optional得到Student
                            ))
            ));
     输出结果：
    "Class A": {"王五", "Class A", 90},
    "Class B": {"李四", "Class B", 72}
}

            */

        //6、distinct()	去重 Stream本身的 distinct() 只能去重整个对象（依赖 equals 和 hashCode），不能按字段去重。

    /*数据去重（自定义字段去重）场景： 根据用户 ID 去重,这么写就可以不用重新对象的 equals 和 hashCode
    List<User> distinctById = users.stream()
            .collect(Collectors.collectingAndThen(
                    Collectors.toMap(
                            User::getId,  // 键提取器（以用户ID作为Map的key）
                            u -> u,      // 值提取器（保留用户对象本身）
                            (u1, u2) -> u1  // toMap()的合并函数（当key冲突时保留第一个用户）
                    ),
                    map -> new ArrayList<>(map.values())  // 将Map的值转换为ArrayList
            ));*/

        //7、parallelStream 并行流（性能优化场景）
        //注意：并行流不是总比普通流快，适合大数据量、无共享资源的场景，调优前需评估


        //7、parallelStream 并行流 底层用的是 ForkJoinPool（默认公共线程池）会把数据集拆分成多个小任务，分配给多个线程并行执行，然后合并结果。一般用于 CPU 密集型、数据量大、任务独立 的场景
        //并行执行时，避免修改共享变量，除非用线程安全的数据结构。
        List<Integer> list2 = Arrays.asList(1, 2, 3, 4, 5);
        int sum1 = list2
                .parallelStream() // 并行执行，如果是打印输出可能无序
                .mapToInt(i -> i * 2)
                .sum();
        System.out.println(sum1);

    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //8、 CompletableFuture异步任务处理 （支持回调）

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> { //runAsync() → 无返回值任务
            System.out.println("执行任务: " + Thread.currentThread().getName());
        });
        future.join(); // 等待完成

        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {//supplyAsync() → 有返回值任务
            System.out.println("计算中: " + Thread.currentThread().getName());
            return 42;
        });

        System.out.println("结果: " + future2.join());

        //链式调用
        CompletableFuture.supplyAsync(() -> "Hello")
                .thenApply(s -> s + " World") // 有返回值
                .thenAccept(System.out::println) // 消费结果
                .thenRun(() -> System.out.println("执行完毕")); // 不接收结果

        //组合多个任务 、异常处理等略

        // 返回的是错误信息
        CompletableFuture<Integer> completableFuture =
                CompletableFuture.supplyAsync(()->{
                    System.out.println(Thread.currentThread().getName()+"supplyAsync=>Integer");
                    int i = 10/0;
                    return 1024;
                });
        System.out.println(completableFuture.whenComplete((t, u) -> {
            System.out.println("t=>" + t); // 正常的返回结果
            System.out.println("u=>" + u); // 错误信息：
        //    java.util.concurrent.CompletionException: java.lang.ArithmeticException: / by zero
        }).exceptionally((e) -> {
            System.out.println(e.getMessage());
            return 233; // 可以获取到错误的返回结果
        }).get());

    }


}
