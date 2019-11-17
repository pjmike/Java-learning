package com.pjmike.effective_java;

public enum Colorful {
    RED {
        @Override
        public String colorName() {
            return "红色";
        }
    },
    YELLOW {
        @Override
        public String colorName() {
            return "红色";
        }
    },
    BLUE {
        @Override
        public String colorName() {
            return "蓝色";
        }
    };
    public abstract String colorName();
}
