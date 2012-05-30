(ns clojure_ttt.game_loop-spec
  (:use [clojure_ttt.game_loop])
  (:use [speclj.core]))

(deftype MockLoopUI [play-again-counter go-first-counter responses]
  LoopUI
  (play-again? [_]
    (let [result (nth responses @play-again-counter)]
      (dosync (ref-set play-again-counter (inc @play-again-counter)))
      result))
  (go-first? [_]
    (let [result (nth responses @go-first-counter)]
      (dosync (ref-set go-first-counter (inc @go-first-counter)))
      result)))

(describe "Game Loop"
  (it "Plays until play-again returns false"
    (def counter (ref 0))
    (do (play (MockLoopUI. counter (ref 0) [true true true false]))
    (should= 4 @counter)))

  (it "Asks if player wants to go first"
    (def counter (ref 0))
    (do (play (MockLoopUI. (ref 0) counter [true true true true false]))
    (should= 5 @counter))))

(run-specs)

