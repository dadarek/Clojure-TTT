(ns clojure_ttt.game_loop)

(defprotocol LoopUI
  (play-again? [ui]))

(defn play [ui]
  (while (play-again? ui) ))

