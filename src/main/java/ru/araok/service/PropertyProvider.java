package ru.araok.service;

import ru.araok.property.RequestProperties;

import java.util.List;

public interface PropertyProvider {
    long getCountContentDownloads();

    List<RequestProperties> getRequests();
}
