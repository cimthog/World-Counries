package com.divineventures.worldcountries;

public class MyData {
    private String name;
    private String flag;
    private String region;
    private String population;

    public MyData(String name, String flag, String population, String region) {
        this.name = name;
        this.flag = flag;
        this.population = population;
        this.region = region;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
