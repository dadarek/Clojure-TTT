(ns clojure_ttt.console_ui
  (:use clojure_ttt.human_player))

(defrecord ConsoleUI []
  NextMoveUI
  (get-next-move [this]
    (loop [result nil]
      (if (and (not= nil result)
               (> 10 result)
               (< 0 result))
        result
      (recur (try (Integer/parseInt (read-line))
                  (catch NumberFormatException e nil)))))))

