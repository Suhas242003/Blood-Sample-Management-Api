package com.example.bsm.utility;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
// builder cannot use
public class PageStructure<T> extends ResponseStructure<T> {
    private int page;
    private int totalPages;
    private int size;


//    public static PageStructureBilder<T> buider{
//
//    }


}