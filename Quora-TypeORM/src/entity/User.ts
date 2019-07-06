import {Entity, PrimaryGeneratedColumn, Column, OneToMany} from "typeorm";
import { Question } from "./Question";
import { Answer } from "./Answer";

@Entity({name: "users"})
export class User {

    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    name: string;

    @Column()
    email: string;

    @OneToMany(type => Question, question => question.user)
    questions: Question[]

    @OneToMany(type => Answer, answer => answer.user)
    answers: Answer[]

}
