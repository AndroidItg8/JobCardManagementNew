package com.itg.jobcardmanagement.registration.mvp.view;

import com.itg.jobcardmanagement.common.common_interface.BaseView;
import com.itg.jobcardmanagement.registration.model.RegistrationModel;

/**
 * Created by Android itg 8 on 8/7/2017.
 */

public interface RegistrationView extends BaseView {

    void onSendRegistrationDetailsToServer(RegistrationModel model);
}
