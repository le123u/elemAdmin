package com.company.view;

import com.company.domain.Business;

public interface BusinessView {

    public void listBusinessAll();

    public void listBusinessSave();

    public void saveBusinessAll();

    public void deleteBusinessAll();

    public Business login();

    public void showBusinessInfo(Integer businessId);

    public void updateBusinessInfo(Integer businessId);

    public void updateBusinessByPassword(Integer businessId);
}
