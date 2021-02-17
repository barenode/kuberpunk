package com.example.demo;

public class Sample {
    
    private int uuid;

    public Sample() {
        super();
    }

    public Sample(int uuid) {
        super();
        this.uuid = uuid;
    }

    public int getUuid() {
        return uuid;
    }

    public void setUuid(int uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "Sample [uuid=" + uuid + "]";
    }
}
