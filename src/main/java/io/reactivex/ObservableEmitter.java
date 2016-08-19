/**
 * Copyright 2016 Netflix, Inc.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is
 * distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See
 * the License for the specific language governing permissions and limitations under the License.
 */

package io.reactivex;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Cancellable;

/**
 * Abstraction over a RxJava Observer that allows associating
 * a resource with it.
 * <p>
 * The onNext, onError and onComplete methods should be called 
 * in a sequential manner, just like the Observer's methods.
 * Use {@link #serialize()} if you want to ensure this. 
 * The other methods are threadsafe.
 *
 * @param <T> the value type to emit
 */
public interface ObservableEmitter<T> {

    /**
     * Signal a value.
     * @param t the value, not null
     */
    void onNext(T t);
    
    /**
     * Signal an exception.
     * @param t the exception, not null
     */
    void onError(Throwable t);
    
    /**
     * Signal the completion.
     */
    void onComplete();
    
    /**
     * Sets a Disposable on this emitter; any previous Disposable
     * or Cancellation will be unsubscribed/cancelled.
     * @param d the disposable, null is allowed
     */
    void setDisposable(Disposable d);
    
    /**
     * Sets a Cancellable on this emitter; any previous Disposable
     * or Cancellation will be unsubscribed/cancelled.
     * @param c the cancellable resource, null is allowed
     */
    void setCancellable(Cancellable c);
    
    /**
     * Returns true if the downstream cancelled the sequence.
     * @return true if the downstream cancelled the sequence
     */
    boolean isCancelled();
    
    /**
     * Ensures that calls to onNext, onError and onComplete are properly serialized.
     * @return the serialized FlowableEmitter
     */
    ObservableEmitter<T> serialize();
}