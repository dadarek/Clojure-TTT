(ns clojure_ttt.console_ui-spec
  (:use clojure_ttt.human_player)
  (:use clojure_ttt.console_ui)
  (:import [clojure_ttt.console_ui ConsoleUI])
  (:use [speclj.core]))


(describe "Console UI"
  (it "Reads console input"
    (with-out-str (with-in-str "5"
      (should= 5
        (get-next-move (ConsoleUI.))))))

  (it "Ignores non-numeric entries"
    (with-out-str (with-in-str "s\n8"
      (should= 8
        (get-next-move (ConsoleUI.))))))

  (it "Ignores values > 9"
    (with-out-str (with-in-str "10\n15\n9"
      (should= 9
        (get-next-move (ConsoleUI.))))))

  (it "Ignores values < 1"
    (with-out-str (with-in-str "0\n-2\n1"
      (should= 1
        (get-next-move (ConsoleUI.))))))

  (it "Prints prompt"
    (should= "Please select a square: "
      (with-out-str (with-in-str "2"
        (should= 2
          (get-next-move (ConsoleUI.)))))))

  (it "Prints prompt until valid square is entered"
    (should= (apply str (repeat 3 "Please select a square: "))
      (with-out-str (with-in-str "hi\nthere\n4"
        (should= 4
          (get-next-move (ConsoleUI.))))))))

(run-specs)