package com.example.todoapp;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Entity
@Table(name = "todolist")
public class ToDo
{


        @Id
        @GeneratedValue(generator = "incrementor")
        @GenericGenerator(name = "incrementor", strategy = "increment")
        @Column(name = "listID", unique = true)
        private int id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }


        @Column(name = "items")

        private String items;

        public String getItem() {
            return items;
        }

        public void setItem(String item) {
            this.items = item;
        }

}
