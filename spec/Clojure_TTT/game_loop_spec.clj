(ns clojure_ttt.game_loop-spec
  (:use [clojure_ttt.game_loop])
  (:use [speclj.core]))

(deftype MockLoopUI [counter responses]
  LoopUI
  (play-again? [_]
    (let [result (nth responses @counter)]
      (dosync (ref-set counter (inc @counter)))
      result)))

(describe "Game Loop"
  (it "Plays until play-again returns false"
    (def counter (ref 0))
    (do (play (MockLoopUI. counter [true true true false]))
    (should= 4 @counter))))

(run-specs)

