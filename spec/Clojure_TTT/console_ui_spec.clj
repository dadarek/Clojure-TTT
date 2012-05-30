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
        (get-next-move (ConsoleUI.))))))

(run-specs)
