(ns clojure_ttt.minimax_injections-spec
  (:use [clojure_ttt.minimax_injections])
  (:use [speclj.core]))

(describe "Minimax Injections"
  (context "Wins"
    (it "Recognizes winning moves."
      (let [context { :player :x
                      :board '(:x :x nil
                               :o :o nil
                               nil nil nil)}]
        (should (wins? 3 context))
      )
    )

    (it "Recognizes non-winning move."
      (let [context { :player :x
                      :board '(:x :o nil
                               :o :x nil
                               nil nil nil)}]
        (should-not (wins? 3 context))
      )
    )

    (it "Works on both players."
      (let [context { :player :o
                      :board '(:x :o nil
                               :o :x nil
                               nil nil nil)}]
        (should-not (wins? 7 context))
      )
    )
  )

  (context "Ties"
    (it "Recognizes a tie."
      (let [context { :player :x
                      :board '(:x :x :o
                               :o :o :x
                               :x :o nil)}]
        (should (ties? 9 context))
      )
    )

    (it "Knows when there is no tie yet"
      (let [context { :player :x
                      :board '(:x :x :o
                               :o :o :x
                               :x nil nil)}]
        (should-not (ties? 9 context))
      )
    )
  )

  (context "Next Context"
    (it "Switches the player"
      (let [get-context #(next-context 0 {:board '(0) :player %} ) ]
        (should= :x (:player (get-context :o)))
        (should= :o (:player (get-context :x)))
      )
    )

    (it "Marks the correct square"
      (let [old-board '(:x  :o  :x
                        nil nil nil
                        nil nil nil)
            new-board '(:x  :o  :x
                        nil :o  nil
                        nil nil nil)
            context {:player :o :board old-board}]
        (should= new-board (:board (next-context 5 context)))
      )
    )

    (it "Playces the correct player"
      (let [old-board '(:o  :o  :x
                        nil nil nil
                        nil nil nil)
            new-board '(:o  :o  :x
                        nil nil nil
                        nil nil :x)
            context {:player :x :board old-board}]
        (should= new-board (:board (next-context 9 context)))
      )
    )
  )
)

(run-specs)
