package pers.yuzhyn.azylee.study.stta.jmhdemo;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>Title: jmhdemo.BenchMark</p>
 * <p>Description: Jmh demo
 * <P>Program: javatraining
 * <P>Copyright:histone.com
 *
 * @author Ma Yinghui
 * @date 2019-11-27 08:58
 */
@BenchmarkMode(Mode.Throughput) // 吞吐量
@OutputTimeUnit(TimeUnit.MILLISECONDS) // 结果所使用的时间单位
@State(Scope.Benchmark) // 每个测试线程分配一个实例
@Fork(1) // 进行的线程数目
@Warmup(iterations = 4) // 先预热4轮
@Measurement(iterations = 2) // 进行10轮测试
public class BenchMark {

    @Param({"10000","100000","1000000"}) // 定义四个参数，之后会分别对这四个参数进行测试
    private int n;


    private List<Integer> array;
    private List<Integer> list;

    @Setup(Level.Trial) // 初始化方法，在全部Benchmark运行之前进行
    public void init() {
        array = new ArrayList<>(0);
        list = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            array.add(i);
            list.add(i);
        }


    }

    @Benchmark
    public void arrayTraverse() {
        for (int i = 0; i < n; i++) {
            array.get(i);
        }
    }

//    @Benchmark
    public void listTraverse() {
        for (int i = 0; i < n; i++) {
            list.get(i);
        }
    }

    @TearDown(Level.Trial) // 结束方法，在全部Benchmark运行之后进行
    public void arrayRemove() {
        for (int i = 0; i < n; i++) {
            array.remove(0);
            list.remove(0);
        }
    }

    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder().include(BenchMark.class.getSimpleName()).build();
        new Runner(options).run();
    }
}