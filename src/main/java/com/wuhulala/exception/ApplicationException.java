/*
 *
 *  * Copyright 2014-2017 Wuhulala.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.wuhulala.exception;

/**
 * 系统整体异常，所有自定义异常必须继承此异常
 *
 * author： wuhulala
 * date： 2017/6/11
 * version: 1.0
 * description: 作甚的
 */
public class ApplicationException extends RuntimeException {

    private String ErrorMessage;

    public ApplicationException(String errorMessage) {
        ErrorMessage = errorMessage;
    }

    public ApplicationException(String message, String errorMessage) {
        super(message);
        ErrorMessage = errorMessage;
    }

    public ApplicationException(String message, Throwable cause, String errorMessage) {
        super(message, cause);
        ErrorMessage = errorMessage;
    }

    public ApplicationException(Throwable cause, String errorMessage) {
        super(cause);
        ErrorMessage = errorMessage;
    }

    public ApplicationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, String errorMessage) {
        super(message, cause, enableSuppression, writableStackTrace);
        ErrorMessage = errorMessage;
    }

    @Override
    public String getMessage() {
        return this.ErrorMessage;
    }

}
