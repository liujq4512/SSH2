package com.thread;


public class TestSynchronized implements Runnable {
	private int count = 10;

	@Override
	public void run() {
		for (int i = 0; i < 10; i++) {
			synchronized (this) {// ����synchronized�ᵼ�³���0/-1 �����,Ҳ�����������ͬ����������
				if (count > 0) {
					try {
						Thread.sleep(1000);
						System.out.println(Thread.currentThread().getName()
								+ ": " + count--);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}

	public synchronized void test() {// ������̹߳���һ����Դ��ʱ����Ҫ����ͬ�������ǹ����ͬ�����ܵ�������
		if (count > 0) {
			try {
				Thread.sleep(1000);
				System.out.println(Thread.currentThread().getName() + ": "
						+ count--);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		TestSynchronized t = new TestSynchronized();
		Thread thread1 = new Thread(t, "1");
		thread1.start();
		Thread thread2 = new Thread(t, "2");
		thread2.start();
		Thread thread3 = new Thread(t, "3");
		thread3.start();
		// Random r = new Random(24);
		// System.out.println(r.nextInt());

	}
}
