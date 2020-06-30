// Copyright (c) .NET Foundation. All rights reserved.
// Licensed under the Apache License, Version 2.0. See License.txt in the project root for license information.

package com.microsoft.signalr;

import java.util.Map;

final class CompletionMessage extends HubMessage {
    private final int type = HubMessageType.COMPLETION.value;
    private Map<String, String> headers;
    private final String invocationId;
    private final Object result;
    private final String error;

    public CompletionMessage(String invocationId, Object result, String error) {
    	this(null, invocationId, result, error);
    }
    
    public CompletionMessage(Map<String, String> headers, String invocationId, Object result, String error) {
    	if (headers != null && !headers.isEmpty()) {
    		this.headers = headers;
    	}
        if (error != null && result != null) {
            throw new IllegalArgumentException("Expected either 'error' or 'result' to be provided, but not both.");
        }
        this.invocationId = invocationId;
        this.result = result;
        this.error = error;
    }
    
    public Map<String, String> getHeaders() {
    	return headers;
    }

    public Object getResult() {
        return result;
    }

    public String getError() {
        return error;
    }

    public String getInvocationId() {
        return invocationId;
    }

    @Override
    public HubMessageType getMessageType() {
        return HubMessageType.values()[type - 1];
    }
}
