(ns clojure_ttt.console_ui-spec
  (:use clojure_ttt.human_player)
  (:use clojure_ttt.runner)
  (:use clojure_ttt.game_loop)
  (:use clojure_ttt.console_ui)
  (:import [clojure_ttt.console_ui ConsoleUI])
  (:use [speclj.core]))


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
      (should= (apply str (repeat 3 "Please select a square: "))
        (with-out-str (with-in-str "hi\nthere\n4"
          (should= 4
            (get-next-move (ConsoleUI.))))))))

  (context "RunnerUI"
    (it "draws an empty board"
      (should= (str " | | " "\n"
                    " | | " "\n"
                    " | | " "\n")
        (with-out-str (redraw (ConsoleUI.) '(nil nil nil
                                             nil nil nil
                                             nil nil nil)))))
    (it "draws a somewhat-filled board"
      (should= (str "x|x|o" "\n"
                    " |x| " "\n"
                    "o|o|x" "\n")
        (with-out-str (redraw (ConsoleUI.) '(:x  :x  :o
                                            nil :x  nil
                                            :o  :o  :x))))))
  (context "LoopUI"
    (it "Reads 'yes' values"
      (with-in-str "y"
        (should= true (play-again? (ConsoleUI.)))))))

(run-specs)
