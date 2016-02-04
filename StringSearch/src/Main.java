import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Main {

	public static void main(String[] args) throws IOException {

		BlockingQueue<String> queue = new ArrayBlockingQueue<>(1000);
		Path stringSearch = Paths.get("E:/testing");
		Files.walk(stringSearch).forEach(filePath -> {

			if (Files.isRegularFile(filePath) && filePath.toString().endsWith(".txt")) {
				Producer fs = new Producer(filePath, queue);
				fs.start();
				Consumer con = new Consumer(queue, "was");
				con.start();
			}
		});

		System.out.println("End!");
	}
}

////
//
// public class Main {
// public static void main(String[] args) {
// CubbyHole c = new CubbyHole();
// Producer p1 = new Producer(c, 1);
// Consumer c1 = new Consumer(c, 1);
// p1.start();
// c1.start();
// }
// }
// class CubbyHole {
// private int contents;
// private boolean available = false;
// public synchronized int get() {
// while (available == false) {
// try {
// wait();
// }
// catch (InterruptedException e) {
// }
// }
// available = false;
// notifyAll();
// return contents;
// }
// public synchronized void put(int value) {
// while (available == true) {
// try {
// wait();
// }
// catch (InterruptedException e) {
// }
// }
// contents = value;
// available = true;
// notifyAll();
// }
// }
//
// class Consumer extends Thread {
// private CubbyHole cubbyhole;
// private int number;
// public Consumer(CubbyHole c, int number) {
// cubbyhole = c;
// this.number = number;
// }
// public void run() {
// int value = 0;
// for (int i = 0; i < 100; i++) {
// value = cubbyhole.get();
// System.out.println("Consumer #"
// + this.number
// + " got: " + value);
// }
// }
// }
//
// class Producer extends Thread {
// private CubbyHole cubbyhole;
// private int number;
//
// public Producer(CubbyHole c, int number) {
// cubbyhole = c;
// this.number = number;
// }
//
// public void run() {
// for (int i = 0; i < 100; i++) {
// cubbyhole.put(i);
// System.out.println("Producer #" + this.number
// + " put: " + i);
// try {
// sleep((int)(Math.random() * 100));
// } catch (InterruptedException e) { }
// }
// }
// }
