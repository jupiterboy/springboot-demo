package com.jupiterboy.springboot.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) throws InterruptedException {



        ZooKeeper zooKeeper = null;
        try {
            final CountDownLatch countDownLatch = new CountDownLatch(1);
            //连接成功后，会回调watcher监听，此连接操作是异步的，执行完new语句后，直接调用后续代码
            //可指定多台服务地址 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (Event.KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
                        if (Event.EventType.None == event.getType() && null == event.getPath()) {
                            countDownLatch.countDown();
                        } else if (event.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                            try {
                                System.out.println("配置已修改，新值为");
                            } catch (Exception e) {
                            }
                        }
                    }

                }
            });
            countDownLatch.await();
            logger.info("【初始化ZooKeeper连接状态....】={}", zooKeeper.getState());



            TimeUnit.SECONDS.sleep(3);
            try {
                System.out.println("获取节点/helloword 数据");
                Stat stat= new Stat();
                byte[] bytes = zooKeeper.getData("/helloworld", new Watcher() {
                    @Override
                    public void process(WatchedEvent event) {

                    }
                }, stat);
                System.out.println(new String(bytes));
            }catch (Exception e){
                e.printStackTrace();
            }


        } catch (Exception e) {
            logger.error("初始化ZooKeeper连接异常....】={}", e);
        }


        try {
            Stat stat =  zooKeeper.exists("/helloworld",new Watcher(){

                @Override
                public void process(WatchedEvent event) {

                }
            });

            if(stat != null){
                System.out.println("节点/helloword已存在");
            }else{
                try {
                    System.out.println("创建节点/helloword");
                    zooKeeper.create("/helloworld", new Date().toString().getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Executors.newSingleThreadScheduledExecutor().schedule(new Runnable() {
            @Override
            public void run() {
                ZooKeeper zooKeeper1 = null;
                try {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    //连接成功后，会回调watcher监听，此连接操作是异步的，执行完new语句后，直接调用后续代码
                    //可指定多台服务地址 127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183
                    zooKeeper1 = new ZooKeeper("127.0.0.1:2181", 3000, new Watcher() {
                        @Override
                        public void process(WatchedEvent event) {
                            if (Event.KeeperState.SyncConnected == event.getState()) {  //zk连接成功通知事件
                                if (Event.EventType.None == event.getType() && null == event.getPath()) {
                                    countDownLatch.countDown();
                                } else if (event.getType() == Event.EventType.NodeDataChanged) {  //zk目录节点数据变化通知事件
                                    try {
                                        System.out.println("配置已修改，新值为");
                                    } catch (Exception e) {
                                    }
                                }
                            }

                        }
                    });
                    countDownLatch.await();
                    logger.info("【初始化ZooKeeper连接状态....】={}", zooKeeper1.getState());

//                    TimeUnit.SECONDS.sleep(3);
                    try {
//                        System.out.println("获取节点/helloword 数据");
                        Stat stat= new Stat();
                        byte[] bytes = zooKeeper1.getData("/helloworld", new Watcher() {
                            @Override
                            public void process(WatchedEvent event) {
                                logger.info("【Watcher监听事件】={}",event.getState());
                                logger.info("【监听路径为】={}",event.getPath());
                                logger.info("【监听的类型为】={}",event.getType()); //  三种监听类型： 创建，删除，更新
                                System.out.println("********************");
//                                logger.info("【监听值为】={}", zkClient.getData(event.getPath(), this));
                            }
                        }, stat);
                        System.out.println(new String(bytes));
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } catch (Exception e) {
                    logger.error("初始化ZooKeeper连接异常....】={}", e);
                }
            }
        }, 1L, TimeUnit.SECONDS);



        TimeUnit.SECONDS.sleep(3);
        try {
            System.out.println("获取节点/helloword 数据");
            Stat stat= new Stat();
            byte[] bytes = zooKeeper.getData("/helloworld", new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            }, stat);
            System.out.println(new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(3);
        try {
            System.out.println("更新节点/helloword 数据");
            //zk的数据版本是从0开始计数的。如果客户端传入的是-1，则表示zk服务器需要基于最新的数据进行更新。如果对zk的数据节点的更新操作没有原子性要求则可以使用-1.
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zooKeeper.setData("/helloworld",new Date().toString().getBytes(),-1);
        } catch (Exception e) {
            e.printStackTrace();
        }



        TimeUnit.SECONDS.sleep(3);
        try {
            System.out.println("获取节点/helloword 数据");
            Stat stat= new Stat();
            byte[] bytes = zooKeeper.getData("/helloworld", new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            }, stat);
            System.out.println(new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }

        TimeUnit.SECONDS.sleep(3);
        try {
            System.out.println("更新节点/helloword 数据");
            //zk的数据版本是从0开始计数的。如果客户端传入的是-1，则表示zk服务器需要基于最新的数据进行更新。如果对zk的数据节点的更新操作没有原子性要求则可以使用-1.
            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
            zooKeeper.setData("/helloworld",new Date().toString().getBytes(),-1);
        } catch (Exception e) {
            e.printStackTrace();
        }


        TimeUnit.SECONDS.sleep(3);
        try {
            System.out.println("重新获取节点/helloword 数据");
            Stat stat= new Stat();
            byte[] bytes = zooKeeper.getData("/helloworld", new Watcher() {
                @Override
                public void process(WatchedEvent event) {

                }
            }, stat);
            System.out.println(new String(bytes));
        }catch (Exception e){
            e.printStackTrace();
        }

//        TimeUnit.SECONDS.sleep(3);
//        try {
//            //version参数指定要更新的数据的版本, 如果version和真实的版本不同, 更新操作将失败. 指定version为-1则忽略版本检查
//            zooKeeper.delete("/helloworld",-1);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        TimeUnit.SECONDS.sleep(3);
//        try {
//            Stat stat =  zooKeeper.exists("/helloworld",new Watcher(){
//
//                @Override
//                public void process(WatchedEvent event) {
//
//                }
//            });
//
//            if(stat == null){
//                System.out.println("节点/helloword不存在");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    }
}
