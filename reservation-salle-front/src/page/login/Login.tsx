import {useEffect, useState} from "react";
import {useNavigate} from "react-router-dom";
import {toast} from 'react-hot-toast';
import axios from "axios";
import "./login.css";
import {LoginType} from "../../model/login.model";
import {useForm} from "react-hook-form";
import {getUriUser} from "../../UrlTools";
import { jwtDecode } from 'jwt-decode';

function Login  ()   {
    const navigate = useNavigate()
    const [userForm, setForm] = useState<LoginType>({
        username: "",
        password: "",
    });

    useEffect(() => {
        /*if (localStorage.getItem('token')!== ""){
            navigate('/');
            toast.success("Vous êtes connecté!");
        }*/
    })

    const {handleSubmit} = useForm();

    function onSubmit(data: any): any{
        axios.post(getUriUser(`login`), userForm).then((response) => {
            if (response.data.status===0){
                const user: string = jwtDecode(response.data.accessToken).sub!;
                localStorage.setItem('token', response.data.accessToken);
                localStorage.setItem('user', user);
                console.log(response.data.accessToken);
                // Mise à jour de l'état avec les données décodées
                toast.success("Vous êtes connecté!");
                navigate('/');
            }else {
                toast.error("Username ou password incorrect!");
            }
        })
    }

    const registerUser = () => {
        //setData({});
        navigate('/register')
    }

    return (
        <div>
            {/* TopBar Component */}
            <div className="login">
                <form onSubmit={handleSubmit(onSubmit)}>
                    <div className="lContainer">
                        <input
                            type="text"
                            placeholder="Email"
                            id="username"
                            value={userForm.username}
                            name={"username"}
                            onChange={(e: any) => {
                                setForm(prevState => ({
                                    ...prevState,
                                    username: e.target.value,
                                }));
                            }}
                            className="lInput"
                        />
                        <input
                            type="password"
                            placeholder="Password"
                            id="password"
                            value={userForm.password}
                            name={"password"}
                            onChange={(e: any) => {
                                setForm(prevState => ({
                                    ...prevState,
                                    password: e.target.value,
                                }));
                            }}
                            className="lInput"
                        />
                        <button onClick={onSubmit} className="lButton">
                            Login
                        </button>
                        <button onClick={registerUser} className="lButton">
                            Register
                        </button>
                    </div>
                </form>
            </div>
        </div>
    );
}
export default Login