/*
 *    Copyright 2021. caoccao.com Sam Cao
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 *
 */

package com.caoccao.javet.values.reference;

import com.caoccao.javet.annotations.CheckReturnValue;
import com.caoccao.javet.enums.V8ValueReferenceType;
import com.caoccao.javet.exceptions.JavetException;
import com.caoccao.javet.values.V8Value;

import java.util.Objects;

@SuppressWarnings("unchecked")
public class V8Module extends V8ValueReference implements IV8Module {
    protected String resourceName;

    public V8Module(long handle) {
        super(handle);
        resourceName = null;
    }

    @Override
    @CheckReturnValue
    public <T extends V8Value> T evaluate(boolean resultRequired) throws JavetException {
        return checkV8Runtime().getV8Internal().moduleEvaluate(this, resultRequired);
    }

    @Override
    @CheckReturnValue
    public V8ValueError getException() throws JavetException {
        return checkV8Runtime().getV8Internal().moduleGetException(this);
    }

    /**
     * Gets namespace.
     * <p>
     * Note: Please avoid calling this API in production environment
     * because its underlying V8 object is not persisted and core dump will take place.
     *
     * @return the namespace
     * @throws JavetException the javet exception
     */
    @CheckReturnValue
    public V8ValueObject getNamespace() throws JavetException {
        return checkV8Runtime().getV8Internal().moduleGetNamespace(this);
    }

    public String getResourceName() {
        return resourceName;
    }

    @Override
    public int getScriptId() throws JavetException {
        return checkV8Runtime().getV8Internal().moduleGetScriptId(this);
    }

    @Override
    public int getStatus() throws JavetException {
        return checkV8Runtime().getV8Internal().moduleGetStatus(this);
    }

    @Override
    public V8ValueReferenceType getType() {
        return V8ValueReferenceType.Module;
    }

    @Override
    public boolean instantiate() throws JavetException {
        return checkV8Runtime().getV8Internal().moduleInstantiate(this);
    }

    public void setResourceName(String resourceName) {
        Objects.requireNonNull(resourceName);
        this.resourceName = resourceName;
    }

    @Override
    @CheckReturnValue
    public V8Module toClone() throws JavetException {
        return this;
    }

    @Override
    public String toString() {
        return resourceName;
    }
}
