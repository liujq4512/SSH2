package com.zookeeper;

import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;

public class DeleteNode implements Watcher{

private static CountDownLatch latch = new CountDownLatch(1);
	
	public static void main(String[] args) {
		createNode();
	}
	public static void createNode() {
		try {
			
			ZooKeeper zk = new ZooKeeper("192.168.15.132:2181,192.168.15.133:2181,192.168.15,134:2181",1000,new ZookeeperTest());
			List<String> list = zk.getChildren("/execute", false);
			System.out.println(list.size());
			for(String s : list){
				zk.delete("/execute/" + s, -1);
			}
			latch.await();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void process(WatchedEvent arg0) {
		latch.countDown();
		System.out.println("change path is : " +arg0.getPath());
		
	}
}
