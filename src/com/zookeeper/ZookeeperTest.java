package com.zookeeper;

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooKeeper.States;

public class ZookeeperTest implements Watcher{
	private static String connStr = "192.168.15.197:2181,192.168.15.198:2181,192.168.15.135:2181";
	public static String connChrootStr = "192.168.15.197:2181,192.168.15.198:2181,192.168.15.135:2181/bpp";
	
	private static CountDownLatch latch = new CountDownLatch(1);
	
	public static void main(String[] args) {
		createNode(connStr);
		
	}
	public static void createNode(String connStr) {
		try {
			ZooKeeper zk = new ZooKeeper(connStr,1000,new ZookeeperTest());
			if(States.CONNECTING == zk.getState()){
				System.out.println(latch.getCount() + " state connecting !!");
				latch.await(); //等待 zookeeper 连接完成latch.countDown() 执行后再进行创建，否则会报错
				System.out.println(latch.getCount() + " connect complete !!");
			}
			zk.create("/bpp", "bpp".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
			zk.create("/root", "root".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			zk.create("/root/children", "root".getBytes(), Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
			Thread.sleep(1000000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
	@Override
	public void process(WatchedEvent arg0) {
		if( arg0.getState()== KeeperState.SyncConnected){
			latch.countDown();
			System.out.println(latch.getCount()+ "  change path is : " +arg0.getPath());
		}
		
	}
	
}
