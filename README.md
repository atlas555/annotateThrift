# annotateThrift

本项目进行thrif源码的注解。
(1)RPC的结构
![rpc结构](http://mindwind.me/assets/article_images/2014-09-22-1.svg)
(摘自 [mindwind.me](http://mindwind.me/blog/2014/09/22/%E6%B7%B1%E5%85%A5%E6%B5%85%E5%87%BARPC%E2%80%94%E2%80%94%E6%B7%B1%E5%85%A5%E7%AF%87.html))

RPC 服务方通过 RpcServer 去导出（export）远程接口方法，而客户方通过 RpcClient 去引入（import）远程接口方法。 客户方像调用本地方法一样去调用远程接口方法，RPC 框架提供接口的代理实现，实际的调用将委托给代理 RpcProxy 。 代理封装调用信息并将调用转交给 RpcInvoker 去实际执行。 

在客户端的 RpcInvoker 通过连接器 RpcConnector 去维持与服务端的通道 RpcChannel， 并使用 RpcProtocol 执行协议编码（encode）并将编码后的请求消息通过通道发送给服务方。
RPC 服务端接收器 RpcAcceptor 接收客户端的调用请求，同样使用 RpcProtocol 执行协议解码（decode）。 解码后的调用信息传递给 RpcProcessor 去控制处理调用过程，最后再委托调用给 RpcInvoker 去实际执行并返回调用结果

# 结构

thrift 源码的结构如下：
  - meta_data
  - transport
  - protocol
  - server
  - async
  - scheme  
  - ex..

对应的，thrift结构如下：
  - TProtocol 协议和编解码组件
  - TTransport 传输组件
  - TProcessor 服务调用组件
  - TServer，Client 服务器和客户端组件
  - IDL 服务描述组件，负责生产跨平台客户端

## meta_data  
