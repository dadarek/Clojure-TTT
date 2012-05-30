(ns clojure_ttt.console_ui-spec
  (:use clojure_ttt.human_player)
  (:use clojure_ttt.console_ui)
  (:import [clojure_ttt.console_ui ConsoleUI])
  (:use [speclj.core]))


(describe "Console UI"
  (it "Reads console input"
    (with-in-str "5"
      (should= 5
        (get-next-move (ConsoleUI.)))))

  (it "Ignores non-numeric entries"
    (with-in-str "s\n8"
      (should= 8
        (get-next-move (ConsoleUI.)))))

  (it "Ignores values > 9"
    (with-in-str "10\n15\n9"
      (should= 9
        (get-next-move (ConsoleUI.)))))

  (it "Ignores values < 1"
    (with-in-str "0\n-2\n1"
      (should= 1
        (get-next-move (ConsoleUI.))))))

(run-specs)
