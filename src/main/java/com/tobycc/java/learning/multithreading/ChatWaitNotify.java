package com.tobycc.java.learning.multithreading;

public class ChatWaitNotify {
   boolean flag = false;

   public synchronized void Question(String msg) {
      if (flag) {
         try {
            wait();
         }catch (InterruptedException e) {
            e.printStackTrace();
         }
      }
      System.out.println(msg);
      flag = true;
      notify();
   }

   public synchronized void Answer(String msg) {
      if (!flag) {
         try {
            wait();
         }catch (InterruptedException e) {
            e.printStackTrace();
         }
      }

      System.out.println(msg);
      flag = false;
      notify();
   }


   public static void main(String[] args) {
      //Test
      ChatWaitNotify m = new ChatWaitNotify();
      new T1(m);
      new T2(m);
   }
}

class T1 implements Runnable {
   ChatWaitNotify m;
   String[] s1 = { "Hi", "How are you ?", "I am also doing fine!" };

   public T1(ChatWaitNotify m1) {
      this.m = m1;
      new Thread(this, "Question").start();
   }

   public void run() {
      for (int i = 0; i < s1.length; i++) {
         m.Question(s1[i]);
      }
   }
}

class T2 implements Runnable {
   ChatWaitNotify m;
   String[] s2 = { "Hi", "I am good, what about you?", "Great!" };

   public T2(ChatWaitNotify m2) {
      this.m = m2;
      new Thread(this, "Answer").start();
   }

   public void run() {
      for (int i = 0; i < s2.length; i++) {
         m.Answer(s2[i]);
      }
   }
}
