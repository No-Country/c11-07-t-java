import { useState } from "react";

export function useLocalStorage (key, initValue) {
    const [storedValue, setStoredValue] = useState(() => {
        try{
            const item = window.localStorage.getItem(key);
            return item ? JSON.parse(item) : initValue;
        }catch (error){
            initValue
        }
    })

    const setValue = value => {
        try{
            setStoredValue(value);
            window.localStorage.setItem(key, JSON.stringify(value));
        }catch (error){
            console.error(error)
        }
    }

    return [storedValue, setValue]
}