/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.thrift.protocol;

import java.nio.ByteBuffer;

import org.apache.thrift.TException;
import org.apache.thrift.scheme.IScheme;
import org.apache.thrift.scheme.StandardScheme;
import org.apache.thrift.transport.TTransport;

/**
 * Protocol interface definition.
 * 抽象类
 * 对于rpc来说，协议就是约定客户端和服务端传输的数据
 * 针对rpc来说，传输的数据包括
 * （1）调用方 - 客户端
 *    1.方法的名称，包括类的名称和方法的名称
 *    2.方法的参数，包括类型和参数值
 *    3.一些附加的数据，比如附件，超时事件，自定义的控制信息等等
 * （2）返回方 - 服务端
 *    1.调用的返回码
 *    2.返回值
 *    3.异常信息
 */
public abstract class TProtocol {

  /**
   * Prevent direct instantiation
   *
   *  SuppressWarnings("unused"):
   *  屏蔽java编译中的一些警告信息。
   *  unused这个参数是屏蔽：定义的变量在代码中并未使用且无法访问。java在编译的时候会出现这样的警告，
   *  加上这个注解之后就是告诉编译器，忽略这些警告，编译的过程中将不会出现这种类型的警告
   */
  @SuppressWarnings("unused")
  private TProtocol() {}

  /**
   * Transport
   * 将TTransport对象作为protected成员，进行关联；
   * TTransport也是一个抽象类
   */
  protected TTransport trans_;

  /**
   * Constructor
   * 以传入 TTransport 对象，构造TProtocol对象，实现协议和编解码放在一起
   */
  protected TProtocol(TTransport trans) {
    trans_ = trans;
  }

  /**
   * Transport accessor
   * 获取该协议的传输信息
   */
  public TTransport getTransport() {
    return trans_;
  }


  /**
   *   定义一系列读写消息的编解码接口
   包含对两大类数据的编解码
   （1）复杂数据结构的，如对 Message 的read、write
   （2）基本数据的编解码，如32、i64、String 等等

pad
   */


  /**
   * Writing methods.
   */

  public abstract void writeMessageBegin(TMessage message) throws TException;

  public abstract void writeMessageEnd() throws TException;

  public abstract void writeStructBegin(TStruct struct) throws TException;

  public abstract void writeStructEnd() throws TException;

  public abstract void writeFieldBegin(TField field) throws TException;

  public abstract void writeFieldEnd() throws TException;

  public abstract void writeFieldStop() throws TException;

  public abstract void writeMapBegin(TMap map) throws TException;

  public abstract void writeMapEnd() throws TException;

  public abstract void writeListBegin(TList list) throws TException;

  public abstract void writeListEnd() throws TException;

  public abstract void writeSetBegin(TSet set) throws TException;

  public abstract void writeSetEnd() throws TException;

  public abstract void writeBool(boolean b) throws TException;

  public abstract void writeByte(byte b) throws TException;

  public abstract void writeI16(short i16) throws TException;

  public abstract void writeI32(int i32) throws TException;

  public abstract void writeI64(long i64) throws TException;

  public abstract void writeDouble(double dub) throws TException;

  public abstract void writeString(String str) throws TException;

  public abstract void writeBinary(ByteBuffer buf) throws TException;

  /**
   * Reading methods.
   */

  public abstract TMessage readMessageBegin() throws TException;

  public abstract void readMessageEnd() throws TException;

  public abstract TStruct readStructBegin() throws TException;

  public abstract void readStructEnd() throws TException;

  public abstract TField readFieldBegin() throws TException;

  public abstract void readFieldEnd() throws TException;

  public abstract TMap readMapBegin() throws TException;

  public abstract void readMapEnd() throws TException;

  public abstract TList readListBegin() throws TException;

  public abstract void readListEnd() throws TException;

  public abstract TSet readSetBegin() throws TException;

  public abstract void readSetEnd() throws TException;

  public abstract boolean readBool() throws TException;

  public abstract byte readByte() throws TException;

  public abstract short readI16() throws TException;

  public abstract int readI32() throws TException;

  public abstract long readI64() throws TException;

  public abstract double readDouble() throws TException;

  public abstract String readString() throws TException;

  public abstract ByteBuffer readBinary() throws TException;

  /**
   * Reset any internal state back to a blank slate. This method only needs to
   * be implemented for stateful protocols.
   */
  public void reset() {}
  
  /**
   * Scheme accessor
   */
  public Class<? extends IScheme> getScheme() {
    return StandardScheme.class;
  }
}
