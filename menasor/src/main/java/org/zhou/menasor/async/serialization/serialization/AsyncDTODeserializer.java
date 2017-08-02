/**
 * Licensed to the Apache Software Foundation (ASF) under one or more contributor license agreements. See the NOTICE
 * file distributed with this work for additional information regarding copyright ownership. The ASF licenses this file
 * to You under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the
 * License. You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package org.zhou.menasor.async.serialization.serialization;

import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.zhou.menasor.async.serialization.dto.AsyncDTO;

import java.util.Map;

/**
 *  String encoding defaults to UTF8 and can be customized by setting the property key.deserializer.encoding,
 *  value.deserializer.encoding or deserializer.encoding. The first two take precedence over the last.
 */
public class AsyncDTODeserializer implements Deserializer<AsyncDTO> {

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to do
    }

    @Override
    public AsyncDTO deserialize(String topic, byte[] data) {
        try {
            if (data == null)
                return null;
            else
                return ProtostuffUtil.deserializer(data, AsyncDTO.class);
        } catch (Exception e) {
            throw new SerializationException("ProtostuffUtil deserialize fail");
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}
