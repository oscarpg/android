package com.telenav.osv.http;


import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import com.android.volley.AuthFailureError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.StringRequest;

/**
 * Created by Kalman on 10/6/2015.
 */
public class DeleteSequenceRequest extends StringRequest {

    private static final String PARAM_SEQUENCE_ID = "sequenceId";

    private static final String PARAM_USER_ID = "externalUserId";

    private static final String PARAM_USER_TYPE = "userType";

    private final Listener<String> mListener;

    private final int mSequenceId;

    private final String mUserId;

    protected Map<String, String> headers;

    private MultipartEntityBuilder mBuilder = MultipartEntityBuilder.create();

    public DeleteSequenceRequest(String url, ErrorListener errorListener, Listener<String> listener, int sequenceId, String userId) {
        super(Method.POST, url, listener, errorListener);
        mUserId = userId;
        mSequenceId = sequenceId;
        mListener = listener;
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        Map<String, String> headers = super.getHeaders();

        if (headers == null
                || headers.equals(Collections.emptyMap())) {
            headers = new HashMap<>();
        }

        headers.put("Accept", "application/json");

        return headers;
    }

    @Override
    protected Map<String, String> getParams() {
        Map<String, String> params = new HashMap<String, String>();
        params.put(PARAM_SEQUENCE_ID, mSequenceId + "");
        params.put(PARAM_USER_ID, mUserId);
        params.put(PARAM_USER_TYPE, "osm");
        return params;
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }
}