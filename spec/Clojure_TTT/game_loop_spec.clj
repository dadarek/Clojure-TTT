(ns clojure_ttt.game_loop-spec
  (:use [clojure_ttt.game_loop])
  (:use [speclj.core]))

(deftype MockLoopUI []
  LoopUI
  (play-again? [x]
    (print "1")
    (= "y" (read-line))))

(describe "Game Loop"
  (it "Plays until user enters 'n'"
    (should= "1111"
      (with-out-str (with-in-str "y\ny\ny\nn"
        (play (MockLoopUI.)))))))

(run-specs)

