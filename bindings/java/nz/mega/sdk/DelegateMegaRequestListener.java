package nz.mega.sdk;

class DelegateMegaRequestListener extends MegaRequestListener {

    MegaApiJava megaApi;
    MegaRequestListenerInterface listener;
    boolean singleListener;

    DelegateMegaRequestListener(MegaApiJava megaApi, MegaRequestListenerInterface listener, boolean singleListener) {
        this.megaApi = megaApi;
        this.listener = listener;
        this.singleListener = singleListener;
    }

    MegaRequestListenerInterface getUserListener() {
        return listener;
    }

    @Override
    public void onRequestStart(MegaApi api, MegaRequest request) {
        if (listener != null) {
            final MegaRequest megaRequest = request.copy();
            megaApi.runCallback(new Runnable() {
                public void run() {
                    listener.onRequestStart(megaApi, megaRequest);
                }
            });
        }
    }

    @Override
    public void onRequestUpdate(MegaApi api, MegaRequest request) {
        if (listener != null) {
            final MegaRequest megaRequest = request.copy();
            megaApi.runCallback(new Runnable() {
                public void run() {
                    listener.onRequestUpdate(megaApi, megaRequest);
                }
            });
        }
    }

    @Override
    public void onRequestFinish(MegaApi api, MegaRequest request, MegaError e) {
        if (listener != null) {
            final MegaRequest megaRequest = request.copy();
            final MegaError megaError = e.copy();
            megaApi.runCallback(new Runnable() {
                public void run() {
                    listener.onRequestFinish(megaApi, megaRequest, megaError);
                }
            });
        }
        if (singleListener) {
            megaApi.privateFreeRequestListener(this);
        }
    }

    @Override
    public void onRequestTemporaryError(MegaApi api, MegaRequest request, MegaError e) {
        if (listener != null) {
            final MegaRequest megaRequest = request.copy();
            final MegaError megaError = e.copy();
            megaApi.runCallback(new Runnable() {
                public void run() {
                    listener.onRequestTemporaryError(megaApi, megaRequest, megaError);
                }
            });
        }
    }
}
