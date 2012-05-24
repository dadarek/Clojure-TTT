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
      (should= :x (:player (next-context nil {:player :o})))
      (should= :o (:player (next-context nil {:player :x})))
    )
  )
)

(run-specs)
