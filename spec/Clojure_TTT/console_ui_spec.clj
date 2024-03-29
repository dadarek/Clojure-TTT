(ns clojure_ttt.console_ui-spec
  (:use clojure_ttt.human_player)
  (:use clojure_ttt.runner)
  (:use clojure_ttt.game_loop)
  (:use clojure_ttt.console_ui)
  (:import [clojure_ttt.console_ui ConsoleUI])
  (:use [speclj.core]))


(defn repeat-string [times text] (apply str (repeat times text)))

(describe "Console UI"
  (context "NextMoveUI"
    (it "Reads next square"
      (with-out-str (with-in-str "5"
        (should= 5
          (get-next-move (ConsoleUI.))))))

    (it "Ignores non-numeric entries"
      (with-out-str (with-in-str "s\n8"
        (should= 8
          (get-next-move (ConsoleUI.))))))

    (it "Ignores values > 9"
      (with-out-str (with-in-str "10\n15\n9"
        (should= 9
          (get-next-move (ConsoleUI.))))))

    (it "Ignores values < 1"
      (with-out-str (with-in-str "0\n-2\n1"
        (should= 1
          (get-next-move (ConsoleUI.))))))

    (it "Prints prompt"
      (should= "Please select a square: "
        (with-out-str (with-in-str "2"
          (should= 2
            (get-next-move (ConsoleUI.)))))))

    (it "Prints prompt until valid square is entered"
      (should= (repeat-string 3 "Please select a square: ")
        (with-out-str (with-in-str "hi\nthere\n4"
          (should= 4
            (get-next-move (ConsoleUI.))))))))

  (context "RunnerUI"
    (it "draws an empty board"
      (should= (str " | | " "\n"
                    " | | " "\n"
                    " | | " "\n\n")
        (with-out-str (redraw (ConsoleUI.) '(nil nil nil
                                             nil nil nil
                                             nil nil nil)))))
    (it "draws a somewhat-filled board"
      (should= (str "X|X|O" "\n"
                    " |X| " "\n"
                    "O|O|X" "\n\n")
        (with-out-str (redraw (ConsoleUI.) '(:x  :x  :o
                                            nil :x  nil
                                            :o  :o  :x)))))
    (it "Announces winner"
      (should= "X wins!\n" (with-out-str (announce-winner (ConsoleUI.) :x))))

    (it "Announces both winners"
      (should= "O wins!\n" (with-out-str (announce-winner (ConsoleUI.) :o))))

    (it "Announces ties"
      (should= "Game tied ...\n" (with-out-str (announce-tie (ConsoleUI.)))))

    (it "Announces next turn"
      (should= "Player X - it is your turn next\n" (with-out-str (announce-next-turn (ConsoleUI.) :x))))

    (it "Announces move taken"
      (should= "Player X takes square 5\n" (with-out-str (announce-next-move-taken (ConsoleUI.) :x 5)))))

  (context "LoopUI"
    (context "play-again?"
      (it "Prints message to console"
        (should= (repeat-string 3 "Would you like to play again?\n")
          (with-out-str (with-in-str "b\nb\ny"
            (do (play-again? (ConsoleUI.)))))))

      (it "Reads 'yes' values"
        (with-out-str (with-in-str "y"
          (should= true (play-again? (ConsoleUI.))))))

      (it "Reads 'no' values"
        (with-out-str (with-in-str "n"
          (should= false (play-again? (ConsoleUI.))))))

      (it "Ignores bogus values"
        (with-out-str (with-in-str "1\n7\nMichael Jackson\nyn\ny"
          (should= true (play-again? (ConsoleUI.))))))

    (context "go-first?"
      (it "Prints message to console"
        (should= (repeat-string 3 "Would you like to go first?\n")
          (with-out-str (with-in-str "b\nb\ny"
            (do (go-first? (ConsoleUI.)))))))

      (it "Reads 'yes' values"
        (with-out-str (with-in-str "y"
          (should= true (go-first? (ConsoleUI.))))))

      (it "Reads 'no' values"
        (with-out-str (with-in-str "n"
          (should= false (go-first? (ConsoleUI.))))))

      (it "Ignores bogus values"
        (with-out-str (with-in-str "533\nx\nOsama Bin Laden\nyn\ny"
          (should= true (go-first? (ConsoleUI.))))))))))

(run-specs)
