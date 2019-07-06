import {Entity, PrimaryGeneratedColumn, Column, OneToMany, ManyToOne, JoinColumn} from "typeorm";
import { User } from "./User";
import { Question } from "./Question";

@Entity({name: "answers"})
export class Answer {

    @PrimaryGeneratedColumn()
    id: number;

    @Column()
    text: string;

    @ManyToOne(type => User, user => user.answers)
    @JoinColumn({name: "user_id"})
    user: User

    @ManyToOne(type => Question, question => question.answers)
    @JoinColumn({name: "question_id"})
    question: Question

}