package org.prudhviraj.bookmyshow.models;
import org.prudhviraj.bookmyshow.models.enums.ShowSeatStatus;
import jakarta.persistence.*;
import lombok.*;


@Setter
@Getter
//Builder
@Entity
public class ShowSeat extends BaseModel {
    /*
        1 -------> 1
        ShowSeat -- Show
        M <------- 1
     */
    @ManyToOne
    private Show show;
    /*
        1 -------> 1
        ShowSeat -- Seat
        M <------- 1
     */
    @ManyToOne
    private Seat seat;
    @Enumerated(EnumType.ORDINAL)
    private ShowSeatStatus showSeatStatus;
}
/**
 Great! Let's break down the diagram you've shown in the image — it's about modeling the relationship between `Show`, `Seat`, and `ShowSeat` in a **BookMyShow-style system**.

 ---

 ## 🎯 Objective

 You want to track **which seats are available/reserved/booked in a particular show**.
 This is modeled using a **`ShowSeat`** entity that connects a specific `Show` and a specific `Seat`.

 ---

 ## 🔄 Entity Relationships

 ### 🔹 `ShowSeat → Show` (Many-to-One)

 * **Meaning**: Each `ShowSeat` is linked to **one specific Show**.
 * But a **Show has many ShowSeats**.
 * Hence:

 ```
 ShowSeat (M) ------ (1) Show
 ```

 ### 🔹 `ShowSeat → Seat` (Many-to-One)

 * **Meaning**: Each `ShowSeat` points to a **specific physical seat** (like A1, A2, A3).
 * But a **Seat** is reused across many shows — so multiple `ShowSeat` entries can point to the same `Seat`.
 * Hence:

 ```
 ShowSeat (M) ------ (1) Seat
 ```

 ---

 ## 📦 Why `ShowSeat` Is Needed

 Let’s say you have:

 * **Shows**: X, Y, Z
 * **Seats**: A1, A2, A3, A4, A5

 You want to track the **status of each seat** in each show. For example:

 | ShowSeat | Show | Seat | Status    |
 | -------- | ---- | ---- | --------- |
 | XA1      | X    | A1   | AVAILABLE |
 | XA2      | X    | A2   | RESERVED  |
 | YA1      | Y    | A1   | SOLD      |
 | ZA1      | Z    | A1   | AVAILABLE |

 This mapping lets you:

 * Know which **seat is sold/reserved** for a particular **show**
 * Reuse the same **Seat entity** across multiple shows
 * Avoid duplicating physical seat data

 ---

 ## 🧠 Analogy

 Think of:

 * `Seat` = a **fixed chair** in a theater (e.g., A1, A2)
 * `Show` = a **specific movie at a specific time**
 * `ShowSeat` = the **status of that chair for that particular show**

 This analogy helps you understand the relationship between `Show`, `Seat`, and `ShowSeat` entities.

 */
