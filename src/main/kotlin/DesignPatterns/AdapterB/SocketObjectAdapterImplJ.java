package DesignPatterns.AdapterB;

import org.jetbrains.annotations.NotNull;

public class SocketObjectAdapterImplJ implements SocketAdapter {
    private USPlug usPlug;
    private UKPlug ukPlug;

    public SocketObjectAdapterImplJ(){
        usPlug = new USPlug();
        ukPlug = new UKPlug();
    }

    public SocketObjectAdapterImplJ(UKPlug ukPlug, USPlug usPlug){
        this.ukPlug = ukPlug;
        this.usPlug = usPlug;
    }

    @NotNull
    @Override
    public Volt convertToUS() {
        return this.usPlug.getVolt();
    }

    @NotNull
    @Override
    public Volt convertToUK() {
        return this.ukPlug.getVolt();
    }
}
