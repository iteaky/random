package com.packagename.myapp.spring.demo;


import java.util.ArrayList;
import java.util.List;


public class IgComments {
    private List<IgComment2> data = new ArrayList<>();
    private Paging paging;


    public IgComments() {
    }

    public IgComments(Paging paging, List<IgComment2> data) {
        this.paging = paging;
        this.data = data;
    }

    public Paging getPaging() {
        return paging;
    }

    public void setPaging(Paging paging) {
        this.paging = paging;
    }

    public List<IgComment2> getData() {
        return data;
    }

    public void setData(List<IgComment2> data) {
        this.data = data;
    }

    public class Paging {

        Cursors cursors;
        String next;

        public Paging() {
        }

        public Paging(Cursors cursors, String next) {

            this.cursors = cursors;
            this.next = next;
        }

        public Cursors getCursors() {
            return cursors;
        }

        public void setCursors(Cursors cursors) {
            this.cursors = cursors;
        }

        public String getNext() {
            return next;
        }

        public void setNext(String next) {
            this.next = next;
        }
    }

    public class IgComment2 {
        private String username;
        private String text;
        private String id;

        public IgComment2(String username, String text, String id) {
            this.username = username;
            this.text = text;
            this.id = id;
        }

        public IgComment2() {
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }
    }

    private class Cursors {
        public Cursors(String after) {
            this.after = after;
        }

        public Cursors() {
        }

        public String getAfter() {
            return after;
        }

        public void setAfter(String after) {
            this.after = after;
        }

        String after;
    }
}
