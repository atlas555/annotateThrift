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

/**
 * Helper class that encapsulates(囊括) struct metadata.
 * 传输的 TMessage 信息结构
 */
public final class TMessage {

  //初始化新message
  //stop = 0
  public TMessage() {
    this("", TType.STOP, 0);
  }

  public TMessage(String n, byte t, int s) {
    name = n;
    type = t;
    seqid = s;
  }

  //字段的含义？
  public final String name;
  public final byte type;
  public final int seqid;

  @Override
  public String toString() {
    return "<TMessage name:'" + name + "' type: " + type + " seqid:" + seqid + ">";
  }

  //重写equal方法
  @Override
  public boolean equals(Object other) {
    if (other instanceof TMessage) {
      return equals((TMessage) other);
    }
    return false;
  }

  public boolean equals(TMessage other) {
    return name.equals(other.name) && type == other.type && seqid == other.seqid;
  }
}
