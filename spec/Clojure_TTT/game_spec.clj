(ns clojure_ttt.game-spec
  (:use [clojure_ttt.game])
  (:use [speclj.core]))

(deftype MockUI []
  GameUI
  (play-again? [x]
    (print "1")
    (= "y" (read-line))))

(describe "Game"
  (it "Plays until user enters 'n'"
    (should= "1111"
      (with-out-str (with-in-str "y\ny\ny\nn"
        (play (MockUI.)))))))

(run-specs)

