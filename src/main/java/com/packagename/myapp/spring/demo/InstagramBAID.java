package com.packagename.myapp.spring.demo;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class InstagramBAID {

    private AGaccount instagram_business_account;

    @NoArgsConstructor
    @Getter
    @Setter
    public class AGaccount {
        private Long id;
    }
}
