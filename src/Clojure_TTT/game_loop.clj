(ns clojure_ttt.game_loop
  (:use clojure_ttt.runner))

(defprotocol LoopUI
  (play-again? [_])
  (go-first? [_]))

(defn play [human computer ui]
  (loop []
    (if (go-first? ui)
      (run human computer ui)
      (run computer human ui))
    (if (play-again? ui)
       (recur))))

