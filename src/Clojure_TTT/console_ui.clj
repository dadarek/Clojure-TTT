(ns clojure_ttt.console_ui
  (:use clojure_ttt.runner)
  (:use clojure_ttt.game_loop)
  (:use clojure_ttt.human_player))

(defn symbol-for [player]
  (cond (= :x player) "X" (= :o player) "O" :default " "))

(defn prompt [message]
  (loop [result nil]
    (if (or (= "y" result) (= "n" result))
      (= "y" result)
      (do
        (println message)
        (recur (read-line))))))

(defrecord ConsoleUI []
  NextMoveUI
  (get-next-move [_]
    (let [is-valid #(and (not= nil %) (> 10 %) (< 0 %))
          try-read #(try (Integer/parseInt (read-line))
                    (catch NumberFormatException e nil)) ]
    (loop []
      (print "Please select a square: ")
      (flush)
      (let [result (try-read)]
        (if (is-valid result)
          result
          (recur))))))

  RunnerUI
  (redraw [_ board]
    (let [row-1 (take 3 board)
          row-2 (drop 3 (take 6 board))
          row-3 (drop 6 board)
          format-row #(apply str (interpose "|" (map symbol-for %)))]
      (do
        (println (format-row row-1))
        (println (format-row row-2))
        (println (format-row row-3))
        (println))))

  (announce-next-turn [_ player]
    (println (str "Player " (symbol-for player) " - it is your turn next")))

  (announce-next-move-taken [_ player square]
    (println (str "Player " (symbol-for player) " takes square " square)))

  (announce-winner [_ winner]
    (println (str (symbol-for winner) " wins!")))

  (announce-tie [_] (println "Game tied ..."))

  LoopUI
  (play-again? [_] (prompt "Would you like to play again?"))
  (go-first? [_] (prompt "Would you like to go first?")))

