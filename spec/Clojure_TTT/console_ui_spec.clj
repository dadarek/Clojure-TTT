(ns clojure_ttt.console_ui-spec
  (:use clojure_ttt.human_player)
  (:import [clojure_ttt.console_ui ConsoleUI])
  (:use [speclj.core]))


(describe "Console UI"
  (it "Compiles"
    (should= 5 
      (get-next-move (ConsoleUI.)))))

(run-specs)
