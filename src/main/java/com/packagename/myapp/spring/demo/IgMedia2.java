package com.packagename.myapp.spring.demo;


public class IgMedia2 {
    private Long comments_count;
    private IgComments comments;



    public IgMedia2(Long comments_count, IgComments comments) {
        this.comments_count = comments_count;
        this.comments = comments;
    }

    public IgMedia2() {
    }

    public Long getComments_count() {
        return comments_count;
    }

    public IgComments getComments() {
        return comments;
    }

    public void setComments_count(Long comments_count) {
        this.comments_count = comments_count;
    }

    public void setComments(IgComments comments) {
        this.comments = comments;
    }
}
