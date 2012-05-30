(ns clojure_ttt.game_loop)

(defprotocol LoopUI
  (play-again? [_])
  (go-first? [_]))

(defn play [ui]
  (loop []
    (go-first? ui)
    (if (play-again? ui)
       (recur))))

