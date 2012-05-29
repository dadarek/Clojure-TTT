(ns clojure_ttt.game)

(defprotocol GameUI
  (play-again? [ui]))

(defn play [ui]
  (while (play-again? ui) ))

